// document.addEventListener('DOMContentLoaded', function(){
//
//     fetch('/carts/nonmember-cart-list.html')
//         .then(response => response.json())
//         .then(data => {
//             var itemThumbNail = data.itemThumbNail;
//             alert('썸네일 : ' + itemThumbNail);
//             var itemName = data.itemName;
//             alert('이름 : ' + itemName);
//             var itemPrice = data.itemPrice;
//             alert('가격 : ' + itemPrice);
//             var itemSize = data.itemSize;
//             alert('사이즈 : ' + itemSize);
//             var itemQuantity = data.itemQuantity;
//             alert('수량 : ' + itemQuantity);
//         })
//         .catch(error => {
//             alert('jsonData 수신 실패 : 사유 - ' + error);
//         })
//
// });