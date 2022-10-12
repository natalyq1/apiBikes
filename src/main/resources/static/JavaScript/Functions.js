function listData() {
    $.ajax({
        url:"http://168.138.89.55:8080/api/Bike/all",//     "http://localhost:8080/api/Bike/all"
        type:"GET",
        dataType: "JSON",
        success:function (response){
            console.log(response)
            paintData(response);
        },
        error:function (response, xhr) {
        alert("Error de petición");
        }
    });
}
function paintData(datos){
    let html="";
    html+="<thead>";
    html+="<tr> <th>Nombre</th>  <th>Marca</th> <th>Año</th> <th>Descripción</th> </tr>";
    html+="</thead>";

    //insertar datos
    html+="<tbody>"
    for (let i=0; i<datos.length; i++){
        html+="<tr> <td>"+datos[i].name+"</td>";
        html+="<td>"+datos[i].brand+"</td>";
        html+="<td>"+datos[i].year+"</td>";
        html+="<td>"+datos[i].category.name+"</td> </tr>";
    }
    html+="</tbody>";

    $("#tableList").empty();
    $("#tableList").append(html);
}