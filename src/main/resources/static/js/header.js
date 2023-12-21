'use strict'

/*헤더 검색 버튼 토글*/
const headerItemSearchLink = document.querySelector('.header__nav-search');

headerItemSearchLink.addEventListener('click',()=>{
    const headerItemSearchContainer = document.querySelector('.nav-search__menus');
    headerItemSearchContainer.classList.toggle('active');
});


/*로그인 헤더 로그아웃*/

const memberLogoutLink = document.querySelector('.member__logout');

memberLogoutLink.addEventListener('click',()=>{
    const memberLogoutForm = document.createElement('form');
    memberLogoutForm.setAttribute('method','post');
    memberLogoutForm.setAttribute('action','/members/logout');
    document.body.appendChild(memberLogoutForm);
    memberLogoutForm.submit();
})
