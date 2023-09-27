
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

class SweetAlert{

fieldsRequired(data){
    Swal.fire({
        title: 'Error!',
        text: data,
        icon: 'error',
        confirmButtonText: 'Ok'
    });
}
success(data){
    Swal.fire({
        title: 'Success',
        text: data,
        icon: 'success',
        confirmButtonText: 'Ok'
    });
}
cancel(data,nav,path){
    Swal.fire({
        title: 'Do you want to cancel the '+data,
        showDenyButton: true,
        confirmButtonText: 'Yes',
        denyButtonText: 'No',
        customClass: {
          actions: 'my-actions',
          cancelButton: 'order-1 right-gap',
          confirmButton: 'order-2',
          denyButton: 'order-3',
        }
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire('Changes are not saved', '', 'info')
          nav(path);
        }
      })
}
deleteData(data,deleteFunction,id){
    Swal.fire({
      title: 'Do you want to delete '+data,
      showDenyButton: true,
      confirmButtonText: 'Yes',
      denyButtonText: 'No',
      customClass: {
        actions: 'my-actions',
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
        denyButton: 'order-3',
      }
    }).then((result) => {
      if (result.isConfirmed) {
        deleteFunction(id)
      }
    })
}
  
}

export default new SweetAlert();