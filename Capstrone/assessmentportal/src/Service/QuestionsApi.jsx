import axios from 'axios';

const QUESTIONS_BASE_URL = 'http://localhost:6002/questions';

class QuestionsApi{

    addQuestion(Quiz){
        return axios.post(QUESTIONS_BASE_URL, Quiz);
    }
    addAssertionQuestion(Quiz){
        return axios.post(QUESTIONS_BASE_URL+'/assetionQuestions', Quiz);
    }
    updateQuestion(id, Question){
        return axios.put(QUESTIONS_BASE_URL+'/que/'+id,Question);
    }
    updateAssertionQuestion(id, Question){
        return axios.put(QUESTIONS_BASE_URL+'/assertion/'+id,Question);
    }

    deleteQuestion(id){
        return axios.delete(QUESTIONS_BASE_URL+'/'+id);
    } 
    getQuestionByQuizId(id){
        return axios.get(QUESTIONS_BASE_URL+'/'+id);
    }
    getByQuestion(Question){
        return axios.get(QUESTIONS_BASE_URL+'/questionByName/'+Question);
    }
}

export default new QuestionsApi();