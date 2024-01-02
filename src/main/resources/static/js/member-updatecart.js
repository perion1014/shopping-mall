
function updateQuantity(changeQuantity, index, cartNo, memberNo, itemNo, itemSize){
    let currentQuantity = document.getElementById('inputvalue_' + index);
    let currentQuantityNum = Number(currentQuantity.value);

    currentQuantityNum += Number(changeQuantity);
        if(currentQuantityNum == 0) {currentQuantityNum = 1;}
        else if(currentQuantityNum == 100) {currentQuantityNum = 99;}
    currentQuantity.value = currentQuantityNum;

    fetch('/carts/' + memberNo + '/' + cartNo + '/update',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            currentQuantityNum: currentQuantityNum,
            cartNo: cartNo,
            memberNo: memberNo,
            itemNo: itemNo,
            itemSize: itemSize,
        }),
    }).then(response => response.json())
        .then(data => {
            var responsemessage = data.response;
            //alert(responsemessage);

            location.reload();
            //alert(currentQuantityNum);
            //alert('전송 결과 : ' + data);
        }).catch(error => {
            alert('전송 실패 - Error : ' + error);
    })

}

function deleteCart(index, cartNo, memberNo){
    let deleteCheck = confirm('장바구니에서 해당 항목을 삭제하시겠습니까?');
    if(deleteCheck == true){
        fetch('/carts/' + memberNo + '/' + cartNo + '/delete',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify({
                cartNo: cartNo,
                memberNo: memberNo,
            }),
        }).then(response => response.text())
            .then(date => {
                alert("삭제되었습니다.");
                location.reload();
            }).catch(error =>{
                alert("삭제에 실패하였습니다 - 사유 : " + error);
        })
    } else{
        alert("취소하셨습니다.");
    }
}

function toggleAllCartCheckbox(className){

    var checkBoxes = document.querySelectorAll('.' + className);
    thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
            for(var i = 0; i < checkBoxes.length; i++){
                checkBoxes[i].checked = thisBoxChecked;
            }

    calculatePriceSum(checkBoxes.length);

}

function calculatePriceSum(cartDTOListSize){

    //alert('자바스크립트로 넘긴 리스트 인덱스 체크 : ' + Number(cartDTOListSize));
    var itemPrice = 0;
    var itemQuantity = 0;
    var priceSum = 0;
    var orderSum = 0;

    for(var i = 0; i < cartDTOListSize; i++){

        if(document.getElementById('cartCheckBox_'+ i).checked === true){

        itemPrice = document.getElementById('itemPrice_' + i).value;
        itemQuantity = document.getElementById('inputvalue_' + i).value;

        priceSum += itemPrice * itemQuantity;
        orderSum = priceSum - 1000;
        }

        //alert('for문돌려서 확인하는 상품 가격 : ' + itemPrice);
        //alert('상품 수량 : ' + itemQuantity);
    }

        document.getElementById('priceSum').innerText = priceSum;
        document.getElementById('orderSum').innerText = orderSum;
}

function checkItemStock(cartDTOListSize, memberNo){

    var jsonData= [];

    var itemName = '';
    var itemSize = '';
    var selectedItemQuantity = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        if(document.getElementById('cartCheckBox_' + i).checked === true){
            itemName = document.getElementById('itemName_' + i).innerText;
            itemSize = document.getElementById('itemSize_' + i).innerText;
            selectedItemQuantity = document.getElementById('inputvalue_' + i)
            var jsonItem = {itemName: itemName, itemSize: itemSize};
            jsonData.push(jsonItem);
            // alert('아이템 명 : ' + itemName);
            // alert('아이템 사이즈 : ' + itemSize);
        }
    }

    // for(var i = 0; i < jsonData.length; i++){
    //     alert('JSON담은 이름 : ' + jsonData[i].itemName);
    //     alert('JSON담은 사이즈 : ' + jsonData[i].itemSize);
    // }

    fetch('/members/' + memberNo + '/orders/check-itemstock', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            alert(JSON.stringify(data.response));

        }).catch(error => {
            alert('JSON 전송 실패 - 사유 : ' + error);
    })
}