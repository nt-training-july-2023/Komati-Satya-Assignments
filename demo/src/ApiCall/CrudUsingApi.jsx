import { useEffect, useState } from "react";
import React from "react";
import axios from "axios";
export const Crud=()=>{
    const[formData,setFormData]=useState(
        {
            userId: "",
            id: "",
            title: "",
            completed:true
        }
    );

    const[data,setData]=useState([]);
    useEffect(()=>{
       axios.get('https://jsonplaceholder.typicode.com/posts').then(
       response=>setData(response.data))
    //    console.log(data);
    }
    );
     
   
     const handleChange=(e)=>{
          setFormData({...formData,[e.target.name]:e.target.value});
    }
    const submitHandler=(e)=>{
        console.log(data);
        e.preventDefault();
        console.log(formData);

        axios.post('https://jsonplaceholder.typicode.com/posts',formData).then(
            res=>{setData([...data,res.data]);
                setFormData({ userId: "", id: "", title: "", body: "" });
            }
           
        )
        console.log(data);
    }
   
    return(
        <>
           <div className="formdiv">
            <form>
                <label>userId</label>
                <input type="text" name="userId" value={formData.userId} placeholder="Enter userId" onChange={handleChange}/><br/>
                <label>ID</label>
                <input type="text" name="id" value={formData.id} placeholder="Enter Id" onChange={handleChange}/><br/>
                <label>Title</label>
                <input type="text" name="title" value={formData.title} placeholder="Enter title" onChange={handleChange}/><br/>
                
                <button className="btn" type="submit" onClick={submitHandler} >Submit</button>
                

                
            </form>
          <table>
            <thead>
            <th>
                userId
            </th>
            <th>
                Id
            </th>
            <th>Title</th>
            </thead>
            <tbody>
            {data.map((item)=>{
                return <tr> <td>{item.userId}</td><td>{item.id}</td><td>{item.title}</td></tr>
            })}
            </tbody>

          
          </table>

           </div>

        </>
    )
}