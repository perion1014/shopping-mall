const homeBtn = document.querySelector('.btn--home');
homeBtn.addEventListener('click',()=>{
    location.href='/';
})

const orderToItemBtn = document.querySelector('.btn--ordertoitems');
orderToItemBtn.addEventListener('click',()=>{
    location.href = '/items/all';
})

function change_form(type) {
    if (type == "1") {
        document.getElementById("display1").style.display = "block";
        document.getElementById("display2").style.display = "none";
    } else if (type == "2") {
        document.getElementById("display1").style.display = "none";
        document.getElementById("display2").style.display = "block";
    }
}
