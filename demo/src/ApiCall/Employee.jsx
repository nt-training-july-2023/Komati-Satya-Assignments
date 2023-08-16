import React, { useState,useEffect } from "react";
import axios from "axios";
import "./emp.css";

export const Employee=()=>{
    
    const[formData,setFormData]=useState({
        employeId:"",
        employeName:"",
        email:"",
        salary:"",
        address:"",
        
});
    const [data,setData]=useState([]);
    const[id,setId]=useState();
useEffect(()=>{
    axios.get('http://localhost:6001/Employee').then(
    res=>setData(res.data)
    )
},[]);
console.log(data);
const postData=(e)=>{
    e.preventDefault();
    axios.post('http://localhost:6001/Employee',formData).then(
        res=>{setData([...data,res.data]);
            setFormData({ userId: "", id: "", title: "", body: "" });
        }
    )
}
const changeData=(e)=>{
    setFormData({...formData,[e.target.name]:e.target.value});
}
console.log(id);
console.log(formData);
console.log(data);
const deleteData=(id1)=>{
    axios.delete(`http://localhost:6001/Employee/${id1}`).then(
        res=>console.log(res.data)
    )
}
const[id2,setId2]=useState({});
const changeId2=(e)=>{
 setId2({...id2,[e.target.name]:e.target.value})
}
console.log(id2);
const updateData=()=>{
    axios.put(`http://localhost:6001/Employee/${id}`,formData).then(
        res=>setFormData(res.data)
    )
}
console.log(data);
const handleEdit = (id) => 
   {
    axios.get(`http://localhost:6001/Employee/${id}`)
        .then(res => {
            setFormData(res.data)

        })
        console.log(formData)
    }
console.log(data);
return(
    <>
    <form>
        <label>Employee Id</label>
        <input type="text" name="employeId" value={formData.employeId} onChange={changeData}/><br/>
        <label>Employee Name</label>
        <input type="text" name="employeName" value={formData.employeName} onChange={changeData}/><br/>
        <label>Employee Email</label>
        <input type="text" name="email" value={formData.email} onChange={changeData}/><br/>
        <label>Employee Salary</label>
        <input type="text" name="salary" value={formData.salary} onChange={changeData}/><br/>
        <label>Employee Address</label>
        <input type="text" name="address" value={formData.address} onChange={changeData}/><br/>
        <button type="submit" onClick={postData}>Post Data</button>
        <button type="submit" onClick={updateData}>Update</button><br/>
        {/* <button type="submit" onClick={()=>deleteData(id.employeId)}>delete</button><br/> */}
         <label>Enter id to search</label>
        <input type="text" name="employeId" value={id2.employeId} onChange={changeId2} />
        <button type="button" onClick={()=>{handleEdit(id2.employeId); setId(id2.employeId)}}>Search</button>
        {/* <button type="submit" onClick={()=>getById(id2.employeId)}>get</button> */}
        {/* <button type="submit" onClick={()=>updateData(id2.employeId)}>update</button> */}

    </form>
    
    <table>
        <th>id</th>
        <th>name</th>
        <th>email</th>
        <th>salary</th>
        <th>addresss</th>
        <tbody>
            {data.map((item)=>{
        return <tr><td>{item.employeId}</td><td>{item.employeName}</td><td>{item.email}</td><td>{item.salary}</td><td>{item.address}</td><td><button type="submit" onClick={()=>deleteData(item.employeId)}>delete</button></td><td> <button type="submit" onClick={()=>{handleEdit(item.employeId); setId(item.employeId)}}>edit</button></td></tr>
            })}
            </tbody>
    </table>
     </>
)

}