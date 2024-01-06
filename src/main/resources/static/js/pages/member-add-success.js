const homeBtnMemberAddSuccess = document.querySelector('.btn--home');
homeBtnMemberAddSuccess.addEventListener('click',()=>{
    location.href='/';
});

const loginBtnMemberAddSuccess = document.querySelector('.btn--login');
loginBtnMemberAddSuccess.addEventListener('click',()=>{
    location.href="/members/login"
});