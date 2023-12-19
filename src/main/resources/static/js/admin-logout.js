'use strict'

const adminLogoutLink = document.querySelector('.admin__logout');

adminLogoutLink.addEventListener('click',()=>{
    const form = document.createElement('form');
    form.setAttribute('method','post');
    form.setAttribute('action','/admins/logout');
    document.body.appendChild(form);
    form.submit();
})