import axios from 'axios';

const USER_BASE_URL = 'http://localhost:6002/student';

class UserApi{

    addUser(User){
        return axios.post(USER_BASE_URL, User);
    }

    getUserById(id){
        return axios.get(USER_BASE_URL+'/'+id);
    }

    loginUser(Login){
        return axios.post(USER_BASE_URL+'/login', Login);
    }
    getAllStudents(){
        return axios.get(USER_BASE_URL+'/students');
    }
    updateUser(id, User){
        return axios.put(USER_BASE_URL+'/'+id,User);
    }
}

export default new UserApi();