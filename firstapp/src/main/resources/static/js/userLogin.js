$(document).ready( function () {
    //
    // const buttonRegister = document.getElementById('btnRegister');
    // buttonRegister.setAttribute('disabled', 'disabled');
    console.log('testing');


})

const loginUser = async () => {

    // const name = document.getElementById('inputFirstName').value;
    // const lastName = document.getElementById('inputLastName').value;
    const email = document.getElementById('inputEmail').value;
    // const phoneNumber = document.getElementById('inputPhoneNumber').value;
    const password = document.getElementById('inputPassword').value;
    // const repeatPassword = document.getElementById('inputRepeatPassword').value;
    let inputData ={ email, password }
    if (email.length >=3 && password.length >= 5)
    {
        const req = await fetch('api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inputData)
        })
        console.log(inputData)

        const res = await req.text();
        console.log(res)
        if (res != "FAIL") {
            localStorage.token = res;
            window.location.href = 'users.html'
        }else {alert('Incorrect loggin attemp, please try again')}
    }else {
        console.log('error')
    }






}