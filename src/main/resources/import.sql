INSERT INTO photos (title,  description, is_visible, img_url) VALUES('Tokyo di sera', 'Foto del bellissimo panorama della capitale Giapponese', 1, 'https://www.aviontourism.com/images/1200-628-Crop/c65038fe-5e8a-481a-8125-edbd9336360f');
INSERT INTO photos (title,  description, is_visible, img_url) VALUES('Roma in bianco e nero', 'Foto in bianco e nero della nostra capitale', 1, 'https://us.123rf.com/450wm/rabbit75123/rabbit751231902/rabbit75123190202104/116894740-il-colosseo-%C3%A8-l-architettura-simbolica-di-roma-e-dell-italia-in-bianco-e-nero.jpg?ver=6');
INSERT INTO categories (name) VALUES('Black and white');
INSERT INTO categories (name) VALUES('City');
INSERT INTO category_photo (photo_id, category_id) VALUES(1, 2);
INSERT INTO category_photo (photo_id, category_id) VALUES(2, 1);
INSERT INTO category_photo (photo_id, category_id) VALUES(2, 2);