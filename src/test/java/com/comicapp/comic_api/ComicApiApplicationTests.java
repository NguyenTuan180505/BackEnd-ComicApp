/*package com.comicapp.comic_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComicApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
*/
//Test thử cái chọn nhạc
package com.comicapp.comic_api;

import com.comicapp.comic_api.entity.Music;
import com.comicapp.comic_api.repository.MusicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComicApiApplicationTests {

    // Tiêm Repository (người thủ kho) vào để dùng
    @Autowired
    private MusicRepository musicRepository;

    @Test
    void testCreateMusic() {
        // 1. Tạo đối tượng Music mới (Tờ khai)
        Music baiHat = new Music();
        baiHat.setName("Chung Ta Cua Tuong Lai"); // Tên bài hát
        baiHat.setUrl("https://zingmp3.vn/bai-hat/chung-ta-cua-tuong-lai"); // Đường dẫn giả lập

        // 2. Lưu vào SQL Server
        musicRepository.save(baiHat);

        // 3. In thông báo ra màn hình Console để kiểm tra
        System.out.println("==================================================");
        System.out.println(">>> KẾT QUẢ TEST:");
        System.out.println(">>> Đã lưu thành công bài hát: " + baiHat.getName());
        System.out.println(">>> ID được tạo trong Database: " + baiHat.getId());
        System.out.println("==================================================");
    }

}