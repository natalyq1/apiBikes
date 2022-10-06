package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Category;
import com.bike.retoBikes.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAll() {
        return categoryRepo.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepo.getCategory(categoryId);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepo.save(category);
        } else {
            Optional<Category> category1 = categoryRepo.getCategory(category.getId());
            if (category1.isPresent()) {
                return category;
            } else {
                return categoryRepo.save(category);
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g= categoryRepo.getCategory(category.getId());
            if(g.isPresent()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoryRepo.save(g.get());
            }
        }
        return category;
    }


    public boolean deleteCategory (int id){
        boolean del = getCategory(id).map(category -> {
            categoryRepo.delete(category);
            return true;
        }).orElse(false);
        return del;
    }
}
