import axios from 'axios';

const CATEGORY_BASE_URL = 'http://localhost:6002/category';

class CategoryApi{

    addCategory(Category){
        return axios.post(CATEGORY_BASE_URL, Category);
    }

    getAllCategories(){
        return axios.get(CATEGORY_BASE_URL);
    }

    getCategoryById(id){
        return axios.get(CATEGORY_BASE_URL+'/cat/'+id);
    }

    updateCategory(id, Category){
        return axios.put(CATEGORY_BASE_URL+'/'+id,Category);
    }

    deleteCategory(id){
        return axios.delete(CATEGORY_BASE_URL+'/'+id);
    } 
}

export default new CategoryApi();