let enlace = document.getElementById('rutLink');

async function registrarUsuarios() {

    let datos = {};
    datos.nombre = "";
    datos.correo = document.getElementById('correo').value;
    datos.contraseña = document.getElementById('contraseña').value;
    datos.imagen = "";

    const request = await fetch('/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'

        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.json();
    console.log(respuesta)
    if (respuesta.nombre != null) {
        localStorage.token = respuesta.nombre;//aqui se esta guardando el token creado 
        localStorage.serie = respuesta.idEsp;
        window.location.href = 'home.html';
    } else if(respuesta.nombre == null){
        alert("Las Credenciales Son Incorrectas!");
    }

}  
