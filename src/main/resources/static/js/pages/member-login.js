'use strict'

const memberAddBtn = document.querySelector('.btn--member-add');

memberAddBtn.addEventListener('click',()=>{
    location.href='/members/add';
});

const loginForm = document.querySelector(".login__form");
const loginBtn = document.querySelector(".btn--member-login");

loginForm.addEventListener('input', (e) => {
    const memberId = document.querySelector(".login__id").value;
    const memberPw = document.querySelector(".login__pw").value;

    if (memberId.length >= 5 && memberPw.length >=8) {
        loginBtn.classList.add('active--btn');
    }
    else {
        loginBtn.classList.remove('active--btn');
    }
});


