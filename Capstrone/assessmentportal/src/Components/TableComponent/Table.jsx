import React from "react";
import '../../Pages/Categories/CategoryStyles.css';
import ButtonComponent from "../Inputs/ButtonComponent";
import { FaList, FaPencilAlt, FaTrash } from "react-icons/fa";
import { Link } from "react-router-dom";

const Table = ({ columns, data, rows, category, deleteData, viewQuizes, role, quiz, question, isTrue }) => {
  return (
    <table className="tableData">
      <thead className="headData">
        <tr className="rowData">
          {columns.map((column) => (
            <th key={column}>{column}</th>
          ))}
          {isTrue && (<>
            <th></th>
            <th></th>
            <th></th></>)}
        </tr>
      </thead>
      <tbody className="bodyData">
        {data.map((item) => (
          <tr>
            {rows.map((column) => (
              <td key={column}>{item[column]}</td>
            ))}

            {category && (<>
              {role === "Admin" && (<>
                <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.categoryId)}><FaTrash className="delete-icon" /> Delete</ButtonComponent></td>
                <td><Link to={`/UpdateCategory/${item.categoryId}`} className="updateData"><FaPencilAlt className="update-icon" /> Update</Link></td>
              </>)}
              {role === "Admin" || role === "student" ? (
                <td> <Link to={`/Quiz/${item.categoryId}`} className="updateData" onClick={() => viewQuizes(item.categoryName, item.categoryId)}>
                  
                  <FaList className="delete-icon" /> Quizes</Link></td>) : (<></>)}
            </>
            )
            }
            {quiz && (<>
              {role === "Admin" && (<>
                <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.quizId)}><FaTrash className="delete-icon" /> Delete</ButtonComponent></td>
                <td><Link to={`/UpdateQuiz/${item.quizId}`} className="updateData"><FaPencilAlt className="update-icon" /> Update</Link></td>
                <td><Link to={`/Questions/${item.quizId}`} className="updateData"><FaList className="delete-icon" /> Questions</Link></td>
              </>)}
              {role === 'student' && <>
                <td><Link to="#" className="updateData" onClick={() => viewQuizes(item.topicName, item.quizId, item.timer, item.categoryId)}>Take Test</Link></td></>}
                
            </>
            )
            }
            {question && (<>
              {role === "Admin" && (<>
                <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.questionId)}><FaTrash className="add-icon" /> Delete</ButtonComponent></td>
                <td><Link to={`/UpdateQuestion/${item.question}`} className="updateData"><FaList className="add-icon" /> Update</Link></td>
              </>)}
            </>)}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default Table;