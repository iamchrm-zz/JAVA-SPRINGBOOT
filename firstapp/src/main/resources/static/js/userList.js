// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUser();
  $('#userList').DataTable();



});
const getHeaders = () => {
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  };
}


const loadUser = async () => {

  const request = await fetch('api/users', {
    method: 'GET',
    headers: getHeaders(),
    // body: JSON.stringify({a: 1, b: 'Textual content'}) FOR POST METHOD
  })
  const users = await request.json();
  //actions icons

  // <a href="#" class="btn btn-primary bclassNamercle btn-sm">
  //   <i class="fas fa-edit"></i>className
  // </a>
  // <a href="#" class="btn btn-warning bclassNamercle btn-sm">
  //   <i class="fas fa-trash"></iclassName
  // </a>
  //list html empty
  let listHtml = ``;
  //delete user button
  for (let userFetch of users) {
    //desectruturacion
    // const {} = userFetch;
    let deleteUserBtn = ` <a href="#" class="btn btn-danger btclassNamecle btn-sm" onclick="deleteUser(${userFetch.id})">
    <i class="fas fa-trash"></i> </a>`
    let userToHtml = `<tr><td> ${userFetch.id}  </td><td>${userFetch.name} ${userFetch.lastName} </td>
                                   <td>${userFetch.email}</td>                                      
                                   <td>${userFetch.phoneNumber}</td>                                                
                                  <td>                                                              
                                       ${deleteUserBtn}                                                                   
                                                                                                        
                                   </td>                                                                      
                                                                                                              
                               </tr>`
    listHtml += userToHtml;
  }



  document.querySelector('#userList tbody').outerHTML = listHtml;
  console.log(users);

}

const deleteUser = async (id) => {

if (!confirm(`are you sure deleting this user id: ${id}`)){
  return;
}
  const response = await fetch(`api/users/${id}`, {
    method: 'DELETE',
    headers: getHeaders(),});
  if(!response.ok) {
    const message = `An error has happend: ${response.status}`;
    throw new Error(message);
  }


  location.reload();

}