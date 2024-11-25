const imagePreview = document.getElementById("imagePreview");
let enlace = document.getElementById('linkLogin');
imagePreview.innerHTML = `<img src="imagenes/usuario.jpg" alt="">`;
let imEstado = "vacio";
document.getElementById("imageUpload").addEventListener("change", function (event) {
    const file = event.target.files[0];

    if (file && file.type.startsWith("image/")) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imagePreview.innerHTML = `<img src="${e.target.result}" alt="Imagen cargada" id="usinIMG">`;
            imEstado = "lleno";
        };
        reader.readAsDataURL(file);
    } else {
        alert("Por favor, selecciona un archivo de imagen.");
    }
});

async function registrarUsuarios(){
    const img = document.getElementById('usinIMG');
    // Crea un canvas para capturar la imagen
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');
    canvas.width = img.width;
    canvas.height = img.height;
    ctx.drawImage(img, 0, 0, img.width, img.height);
    const base64Image = canvas.toDataURL('image/jpeg').split(',')[1];

    let datos = {};
    datos.nombre = document.getElementById('nombre').value;
    datos.correo = document.getElementById('correo').value;
    datos.contraseña = document.getElementById('contraseña').value;
    datos.idEsp = document.getElementById('serie').value;
    datos.imagen = base64Image;
    console.log("Base64 Generado:", datos.imagen);
    let petircontra = document.getElementById('confContraseña').value;

    if(petircontra != datos.contraseña){
        alert("La contraseña es diferente");
        return;
    }

    if(imEstado != "vacio"){
        const request = await fetch('/registrar',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(datos)
        });

        if (request.ok) {
            const responseData = await request.json();
            if(responseData.Respuesta != "BAD"){
                //enlace.href ='../index.html';
               window.location.href ='../index.html';
            }else{
                alert("ESTE CORREO YA EXISTEN...");
                return;
            }
            console.log(responseData.Respuesta);  // Esto imprimirá "Registrado" si la respuesta incluye esta clave
        }else{
            alert("Intentelo de Nuevo")
            return;
        }
    }



}

