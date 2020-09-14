package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently
 * provide any additional behavior over the @Component annotation, but it's a good idea
 * to use @Service over @Component in service-layer classes because it specifies intent
 * better. Additionally, tool support and additional behavior might rely on it in the
 * future.
 * */

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	/*
	 * Autowiring should be implemented for the CategoryRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/*
	 * This method should be used to save a new category.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public Category createCategory(Category category) throws CategoryNotCreatedException {
		Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
		if (optionalCategory.isPresent()) {
			throw new CategoryNotCreatedException("Category already exists");
		}
		Category category1 = categoryRepository.insert(category);
		if (category1 == null) {
			throw new CategoryNotCreatedException("Category not created");
		}
		return category1;
	}

	/*
	 * This method should be used to delete an existing category.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(categoryId);
			return true;
		} else {
			throw new CategoryDoesNoteExistsException("Category does not exist");
		}
	}

	/*
	 * This method should be used to update a existing category.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public Category updateCategory(Category category, String categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			categoryRepository.save(category);
			return category;
		}
		return null;
	}

	/*
	 * This method should be used to get a category by categoryId.Call the
	 * corresponding method of Respository interface.
	 */

	@Override
	public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
		Category category;
		try {
			category = categoryRepository.findById(categoryId).get();
		} catch (NoSuchElementException e) {
			throw new CategoryNotFoundException("Category Not Found");
		}
		return category;
	}

	/*
	 * This method should be used to get a category by userId.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public List<Category> getAllCategoryByUserId(String userId) {
		return categoryRepository.findAllCategoryByCategoryCreatedBy(userId);
	}

}
