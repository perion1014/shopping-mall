'use strict'
function logout() {
    let f = document.createElement('form');
    //폼태그로 감쌀 필요 없다.. 그냥 a태그 해도 됨..
    f.setAttribute('method', 'post');
    f.setAttribute('action', '/logout');
    document.body.appendChild(f);
    f.submit();
}