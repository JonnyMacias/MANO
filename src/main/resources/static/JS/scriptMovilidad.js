async function registrarUsuarios(){
    let datos = {};
    datos.idEsp = "123"
    datos.palabra = document.getElementById('letras').value;
    

    if(datos.palabra != ""){
        const request = await fetch('/letras',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(datos)
        });
        alert(request)
    }
    
    

}