import axios from 'axios';

const QUIZ_BASE_URL = 'http://localhost:6002/quiz';

class QuizApi{

    addQuiz(Quiz){
        return axios.post(QUIZ_BASE_URL, Quiz);
    }

    getQuizByCategoryId(id){
        return axios.get(QUIZ_BASE_URL+'/quizz/'+id);
    }

    updateQuiz(id, Quiz){
        return axios.put(QUIZ_BASE_URL+'/'+id,Quiz);
    }

    deleteQuiz(id){
        return axios.delete(QUIZ_BASE_URL+'/'+id);
    } 
    getQuiz(id){
        return axios.get(QUIZ_BASE_URL+'/id/'+id);
    }
}

export default new QuizApi();