$(document).ready( function () {
 //
 // const buttonRegister = document.getElementById('btnRegister');
 // buttonRegister.setAttribute('disabled', 'disabled');



})

const userRegister = async () => {

    const name = document.getElementById('inputFirstName').value;
    const lastName = document.getElementById('inputLastName').value;
    const email = document.getElementById('inputEmail').value;
    const phoneNumber = document.getElementById('inputPhoneNumber').value;
    const password = document.getElementById('inputPassword').value;
    const repeatPassword = document.getElementById('inputRepeatPassword').value;
    let inputData ={ name, lastName, email, phoneNumber, password, repeatPassword }
    if (name.length >=1 && lastName.length >=1 && email.length >=5 && phoneNumber.length >=9 && password.length >= 5 && password == repeatPassword)
    {
        const res = await fetch('api/users', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inputData)
        })
        console.log(inputData)

    }else {
        console.log('error')
    }






}