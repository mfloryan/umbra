package mmsquare.umbra

import org.springframework.web.multipart.commons.CommonsMultipartFile

class PictureService {

    static transactional = true

	List<Picture> getAvailablePictures() {
		Picture.executeQuery("select p from Picture p where p.id not in (select p2.id from Entry e left join e.pictures as p2)")
	}

	def saveUploadedPictures(List<CommonsMultipartFile> pictures) {

	}
  
}
