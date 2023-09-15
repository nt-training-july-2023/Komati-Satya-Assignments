import axios from 'axios';

const RESULT_BASE_URL = 'http://localhost:6002/finalResult';

class FinalResultApi{

    getResultByStudentId(id){
        return axios.get(RESULT_BASE_URL+'/'+id);
    } 
    getAllResult(){
        return axios.get(RESULT_BASE_URL);
    }
}

export default new FinalResultApi();