
import Swal from "sweetalert2";

class SweetAlert{

fieldsRequired(data){
    Swal.fire({
        title: 'Error!',
        text: data,
        icon: 'error',
        showConfirmButton: false,
        timer:1500,
        // confirmButtonColor: 'rgb(226, 91, 91)',
    });
}
success(data){
    Swal.fire({
        title: 'Success',
        text: data,
        icon: 'success',
        showConfirmButton: false,
        timer:1500,
    });
}
cancel(data,nav,path){
    Swal.fire({
        title: 'Do you want to cancel the '+data,
        showDenyButton: true,
        confirmButtonText: 'Yes',
        denyButtonText: 'No',
        confirmButtonColor: '#5dcc5d',
        customClass: {
          actions: 'my-actions',
          cancelButton: 'order-1 right-gap',
          confirmButton: 'order-2 confirm',
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
      confirmButtonColor: '#5dcc5d',
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
timeOut(){
  Swal.fire({
    title: 'Time Out',
    text: 'All answers are saved',
    icon: 'warning',
    showConfirmButton: false,
        timer:1500,
  })
}

logout(nav){
  Swal.fire({
    title: 'Do you want to logout page??',
    showDenyButton: true,
    confirmButtonText: 'Yes',
    denyButtonText: 'No',
    confirmButtonColor:'#5dcc5d',
    customClass: {
        actions: 'my-actions',
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
        denyButton: 'order-3',
    }
}).then((result) => {
    if (result.isConfirmed) {
      // this.success("logged out..")
        localStorage.removeItem('userRole');
        localStorage.removeItem('userEmail')
        localStorage.removeItem('userName')
        localStorage.removeItem('userId')
        localStorage.removeItem('categoryName')
        localStorage.removeItem('quizName')
        localStorage.removeItem('timerValue');  
        localStorage.removeItem('user');
        localStorage.removeItem('timer');
        localStorage.removeItem('categoryId');  
        nav('/')

    } else if (result.isDenied) {

    }
})
}
  
}

export default new SweetAlert();