'use strict'

const memberLogoutLink = document.querySelector('.member__logout');

memberLogoutLink.addEventListener('click',()=>{
    const form = document.createElement('form');
    form.setAttribute('method','post');
    form.setAttribute('action','/members/logout');
    document.body.appendChild(form);
    form.submit();
})
