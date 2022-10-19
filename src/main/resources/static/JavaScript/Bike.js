$(document).ready(function (){
    getCategoryList();
    getBikes();
});

function getData(){
    let k={
        id:$("#idBike").val(),
        name:$("#nameBike").val(),
        brand:$("#brandBike").val(),
        year:$("#yearBike").val(),
        description:$("#descriptionBike").val(),
        category:{
            id:$("#categorySelect").val()
        }
    }
    return k;
}
function cleanFields(){
    $("#idBike").val("");
    $("#nameBike").val("");
    $("#brandBike").val("");
    $("#yearBike").val("");
    $("#descriptionBike").val("");
    $("#categorySelect").val("").change();
}


function getCategoryList(){
    $.ajax({
        url : "api/Category/all",
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            console.log(p);
            $("#categorySelect").empty();
            for(let i=0;i<p.length;i++){
                let s=`
                    <option value="${p[i].id}">${p[i].name}</option>                
                `;
                $("#categorySelect").append(s);

            }

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}

function saveBike(){
    let data=getData();
    data.id=null;
    let dataToSend=JSON.stringify(data);
    $.ajax({
        url : "api/Bike/save",
        type : 'POST',
        dataType : 'json',
        contentType:'application/json',
        data:dataToSend,
        success : function(p) {
            console.log(p);
            cleanFields();
            getBikes();

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}

function getBikes(){
    $.ajax({
        url : "api/Bike/all",
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            console.log(p);
            $("#results").empty();
            let l="";
            for (let i=0;i<p.length;i++){
                l+=`
<div class="col-sm-7 d-flex align-items-center">
<div class="row d-flex">
                        <div class="card text-bg-secondary border-info m-2 p-2">
                        <div class="card-header text-bg-dark p-3">
                                    <h5 class="card-title text-center text-info">${p[i].name}</h5>
                                </div>
                                <div class="card-body">
                                    <p class="card-text"><strong>Marca:</strong> ${p[i].brand}</p>
                                    <p class="card-text"><strong>Año:</strong> ${p[i].year}</p>
                                    <p class="card-text"><strong>Descripción:</strong> ${p[i].description}</p>
                                    <p class="card-text"><strong>Categoría:</strong> ${p[i].category.name}</p>
                                </div>
                                <div class="card-footer text-bg-secondary p-3">
                                      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                        <button type="button" class="btn btn-outline-info" onclick='deleteBikeById(${p[i].id})'>Eliminar</button>
                                        <button type="button" class="btn btn-info" onclick='getBikeId(${p[i].id})'>Actualizar</button>
                                        
                                    </div>
                                
                                </div>
                        </div>
                    </div>
                    </div>
                    `;
            }
            $("#results").append(l);
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });

}
function getBikeId(idBike){
    $(".saveBike").hide();
    $(".updateBike").show();
    $.ajax({
        url : "api/Bike/"+idBike,
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            console.log(p);
            $("#idBike").val(p.id);
            $("#nameBike").val(p.name);
            $("#brandBike").val(p.brand);
            $("#yearBike").val(p.year);
            $("#descriptionBike").val(p.description);
            $("#categorySelect").val(p.category.id).change();

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });

}
function updateBike(){
    let data=getData();

    let dataToSend=JSON.stringify(data);
    $.ajax({
        url : "api/Bike/update",
        type : 'PUT',
        dataType : 'json',
        contentType:'application/json',
        data:dataToSend,
        success : function(p) {
            console.log(p);
            cleanFields();
            getBikes();
            $(".saveBike").show();
            $(".updateBike").hide();
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}
function cancelUpdateBike(){
    cleanFields();
    $(".saveBike").show();
    $(".updateBike").hide();
}
function deleteBikeById(idBike) {
    $.ajax({
        url: "api/Bike/" + idBike,
        type: 'DELETE',
        dataType: 'json',
        success: function (p) {
            console.log(p);
            getBikes();
        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        },
        complete: function (xhr, status) {
            //  alert('Petición realizada');
        }
    });
}