package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.ItemPhotos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPhotosDTO {

    private Long itemNo;
    private String itemThumb;
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    private String itemThumbModified;
    private String itemImg1Modified;
    private String itemImg2Modified;
    private String itemImg3Modified;

    public static ItemPhotosDTO toItemPhotosDTO(ItemPhotos itemPhotos) {
        ItemPhotosDTO itemPhotosDTO = new ItemPhotosDTO();
        itemPhotosDTO.setItemNo(itemPhotos.getItemNo());
        itemPhotosDTO.setItemThumb(itemPhotos.getItemThumb());
        itemPhotosDTO.setItemImg1(itemPhotos.getItemImg1());
        itemPhotosDTO.setItemImg2(itemPhotos.getItemImg2());
        itemPhotosDTO.setItemImg3(itemPhotos.getItemImg3());
        itemPhotosDTO.setItemImg1Modified(itemPhotos.getItemImg1Modified());
        itemPhotosDTO.setItemImg2Modified(itemPhotos.getItemImg2Modified());
        itemPhotosDTO.setItemImg3Modified(itemPhotos.getItemImg3Modified());
        return itemPhotosDTO;
    }

    public static ItemPhotos toItemPhotos(Long itemNo, ItemPhotosDTO itemPhotosDTO) {
        ItemPhotos itemPhotos = new ItemPhotos();
        itemPhotos.setItemNo(itemNo);
        itemPhotos.setItemThumb(itemPhotosDTO.getItemThumb());
        itemPhotos.setItemImg1(itemPhotosDTO.getItemImg1());
        itemPhotos.setItemImg2(itemPhotosDTO.getItemImg2());
        itemPhotos.setItemImg3(itemPhotosDTO.getItemImg3());
        itemPhotos.setItemThumbModified(itemPhotosDTO.getItemThumbModified());
        itemPhotos.setItemImg1Modified(itemPhotosDTO.getItemImg1Modified());
        itemPhotos.setItemImg2Modified(itemPhotosDTO.getItemImg2Modified());
        itemPhotos.setItemImg3Modified(itemPhotosDTO.getItemImg3Modified());
        return itemPhotos;
    }
}
