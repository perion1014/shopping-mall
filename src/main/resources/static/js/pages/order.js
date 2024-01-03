const homeBtn = document.querySelector('.btn--home');
homeBtn.addEventListener('click',()=>{
    location.href='/';
})

const orderToItemBtn = document.querySelector('.btn--ordertoitems');
orderToItemBtn.addEventListener('click',()=>{
    location.href = '/items/all';
})

