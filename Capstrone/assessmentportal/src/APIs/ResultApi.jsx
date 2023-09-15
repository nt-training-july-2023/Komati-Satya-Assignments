import axios from 'axios';

const RESULT_BASE_URL = 'http://localhost:6002/result';

class ResultApi{

    addResult(Result){
        return axios.post(RESULT_BASE_URL, Result);
    }
}

export default new ResultApi();