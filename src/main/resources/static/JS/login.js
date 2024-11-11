let enlace = document.getElementById('rutLink');

async function registrarUsuarios(){

    let datos = {};
    datos.nombre = "";
    datos.correo = document.getElementById('correo').value;
    datos.contraseña = document.getElementById('contraseña').value;
    datos.imagen = "";

        const request = await fetch('/login',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(datos)
        });
    if (request.ok) {

        const responseData = await request.json();
        console.log(responseData);
        if(responseData.Respuesta != "BAD"){
            enlace.href = 'home.html';
        }else{
            alert("USUARIO O CONTRASEÑA INCORRECTOS")
        }
        console.log(responseData.Respuesta);  // Esto imprimirá "Registrado" si la respuesta incluye esta clave
    }else{
        alert("USUARIO O CONTRASEÑA INCORRECTOS")
    }

    }  
