const menu = document.querySelector('.iconmenu');
const menuList = document.querySelector('.nav__list');
const links = document.querySelectorAll('.links');
const serie = localStorage.getItem("serie");
const boton = document.getElementById("botonPower");

menu.addEventListener('click', function () {
    console.log("ccccccs")
    menuList.classList.toggle('nav__list--show');

});

links.forEach(function (link) {
    link.addEventListener('click', function () {
        menuList.classList.remove('nav__list--show');
    })
});

async function getDatos() {
    const request = await fetch('/getCam/' + serie, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const camara = await request.json();
    console.log(camara);
    if (camara != null) {
        if (camara.Respuesta == "Iguales") {
            estado = {};
            estado.imagen = "Diferentes"
            const request = await fetch('/setCamara/' + serie, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(estado)
            });
            boton.src = "../imagenes/OFF.svg";
        } else {
            estado = {};
            estado.imagen = "Iguales"
            const request = await fetch('/setCamara/' + serie, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(estado)
            });
            boton.src = "../imagenes/On.svg";
        }
    } else {
        estado = {};
        estado.imagen = "Diferentes"
        const request = await fetch('/setCamara/' + serie, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(estado)
        });
        boton.src = "../imagenes/OFF.svg";
    }

};

async function setEstadoNull() {
    const request = await fetch('/getCam/' + serie, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const camara = await request.json();
    console.log(camara);
    if (camara == null) {
        estado = {};
        estado.imagen = "Diferentes"
        const request = await fetch('/setCamara/' + serie, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(estado)
        });
    }

}

async function getEstadoCam() {
    const request = await fetch('/getCam/' + serie, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const camara = await request.json();
    if (camara.Respuesta == "Iguales") {
        boton.src = "../imagenes/On.svg";
    } else {
        boton.src = "../imagenes/OFF.svg";
    }
}
document.addEventListener("DOMContentLoaded", setEstadoNull);
//setEstadoNull();
setInterval(getEstadoCam, 1000);
//window.otraFuncion = getEstadoCam;