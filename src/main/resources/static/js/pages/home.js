const memberAddBtn = document.querySelector('.btn--member-add');

memberAddBtn.addEventListener('click',()=>{
    location.href='/members/add';
});

const noticeBtn = document.querySelector('.btn--notice');
noticeBtn.addEventListener('click',()=>{
    location.href='/notice';
})

const itemsBtn = document.querySelector('.btn--items');
itemsBtn.addEventListener('click',()=>{
    location.href='/items';
})
