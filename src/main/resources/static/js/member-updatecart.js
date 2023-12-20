alert('js 적용 확인');

function updateQuantity(changeQuantity, index){
    alert('버튼함수 진입');
    alert('inputvalue_'+index);
    let currentQuantity = document.getElementById('inputvalue_' + index).value;
    alert('inputvalue : ' + currentQuantity);
}