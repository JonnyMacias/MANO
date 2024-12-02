const serie1 = localStorage.getItem("serie");

async function setLetras() {
    const palabra = document.getElementById('letras');
    let datos = {};
    datos.idEsp = "123"
    datos.palabra = palabra.value;
    palabra.value = "";

    if (datos.palabra != "") {
        const request = await fetch('/letras', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(datos)
        });
    }

};

const emotes = document.querySelectorAll('.emotes');

// Agregar un evento de clic a cada <div>
async function setMovi(event) {
    // Encontrar el <h5> dentro del <div> clicado
    const index = Array.from(emotes).indexOf(this);
    const h5Text = this.querySelector('.getEmote').textContent;
  
    // Crear el objeto datos
    let datos = {
      idEsp: serie1,
      palabra: ""+index,
    };
  
      try {
        const response = await fetch('/letras', {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(datos),
        });
  
        const result = await response.json();
      } catch (error) {
        console.error('Error en la solicitud:', error);
        alert('Ocurrió un error al enviar los datos.');
      }
  }
  
  // Asignar el evento de clic a cada <div> solo una vez
  emotes.forEach((emote) => {
    emote.addEventListener('click', setMovi);
  });