//alert('nonmember-updatecart.js 적용 확인');

function updateQuantity(changeQuantity, cartListIndex){
    let currentQuantityInput = document.getElementById('inputvalue_' + cartListIndex);
    let currentQuantityValue = Number(currentQuantityInput.value);
    //alert(currentQuantityValue);
    if(changeQuantity == 1){
        if(currentQuantityValue != 99){
            currentQuantityValue += Number(changeQuantity);
        }
    } else if(changeQuantity == -1){
        if(currentQuantityValue != 1){
            currentQuantityValue += Number(changeQuantity);
        }
    }

    currentQuantityInput.value = currentQuantityValue;

    fetch('/carts/' + cartListIndex + '/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            changeQuantity: changeQuantity,
            cartListIndex: cartListIndex,
        }),
    }).then(response => response.text())
        .then(data => {
            //alert('JSON JAVA로 전송 성공');
        }).catch(error => {
            alert('JSON 전송 실패 - 사유 : ' + error);
    })

}

function sendCartDeleteJson(cartNo){

    fetch('/carts/' + (cartNo+1) + '/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            cartNo: cartNo,
        }),

    }).then(response => response.text())
        .then(data => {
            alert('삭제되었습니다.');
            location.reload();
        }).catch(error => {
            alert('JSON 전송 실패 : 사유 - ' + error);
    })
}

function toggleAllCartCheckbox(checkBoxClass){

    var checkBoxes = document.querySelectorAll('.' + checkBoxClass);
    thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
    for(var i = 0; i < checkBoxes.length; i++){
        checkBoxes[i].checked = thisBoxChecked;
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

    //alert('자바스크립트로 넘긴 리스트 인덱스 n 체크 : ' + Number(cartDTOListSize));
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

function checkItemStock(cartDTOListSize){

    var jsonData= [];

    var itemName = '';
    var itemSize = '';
    var selectedItemQuantity = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        if(document.getElementById('cartCheckBox_' + i).checked === true){
            itemName = document.getElementById('itemName_' + i).innerText;
            itemSize = document.getElementById('itemSize_' + i).innerText;
            selectedItemQuantity = document.getElementById('inputvalue_' + i).value;

            alert('itemName : ' + itemName);
            alert('itemSize : ' + itemSize);
            alert('selectedItemQuantity : ' + selectedItemQuantity);

            var jsonItem = {itemName: itemName, itemSize: itemSize, selectedItemQuantity : selectedItemQuantity};
            jsonData.push(jsonItem);
            // alert('아이템 명 : ' + itemName);
            // alert('아이템 사이즈 : ' + itemSize);
        }
    }

    // for(var i = 0; i < jsonData.length; i++){
    //     alert('JSON담은 이름 : ' + jsonData[i].itemName);
    //     alert('JSON담은 사이즈 : ' + jsonData[i].itemSize);
    // }

    fetch('/orders/check-itemstock', {
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