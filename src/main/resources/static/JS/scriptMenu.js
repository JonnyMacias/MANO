const menu = document.querySelector('.iconmenu');
const menuList = document.querySelector('.nav__list');
const links = document.querySelectorAll('.links');

menu.addEventListener('click', function () {
    console.log("ccccccs")
    menuList.classList.toggle('nav__list--show');
    
});

links.forEach(function (link) {
    link.addEventListener('click', function () {
        menuList.classList.remove('nav__list--show');
    })
});