Santeri Sajari  Harjoitustyö Ohjelmointi2

Harjoitustyön tavoitteena oli tehdä Chinook tietokantaan pohjautuva dynaaminen verkkosivusto, jolla voi selata tietokannan artisteja, sekä heidän albumeita.

Sivuosto on rakennettu embedd-tomcat-templaten pohjalle https://github.com/ohjelmointi2/embedded-tomcat-template

Lista artisteista toimii sivuston etusivuna ja artistit linkkeinä heidän omille sivuillensa, josta löytää artistin albumit. Albumit toimivat, artistin tavoin linkkenä albumin omalle sivulle, josta näkee albumin kappaleet. Artisti/albumi sivulla on nappi, jolla kyseinen artisti/albumi voidaan poistaa tietokannasta. Artistilistalta artisteja voi lisätä kantaan ja artistin sivulta voidaan lisätä albumeita kantaan, jotka merkitään artistille. Artistilista sivulla on yläkulmassa hakupalkki, jolla voidaan hakea tietokannasta artisteja nimellä/merkkijonolla. Haku palauttaa listan vastaavista artisteista ja esittää ne sivulla.

src
    --main
        --java
            --launch
                ->main.java
            --database
                ->JDBCAlbumDao.java
                ->JDBCArtistDao.java
                ->SQLConnection.java
            --model
                ->AlbumItem.java
                ->ArtistItem.java
            --servlet
                ->AlbumServlet.java
                ->ArtistListServlet.java
                ->ArtistServlet.java
                ->IndexServlet.java
        --resources
        --webapp
            --scripts
                ->app.js
            --styles
                ->denmo.css
            --WEB-INF
                --artists
                    ->album.jsp
                    ->artist.jsp
                    ->artistList.jsp
                    ->artistListSearch.jsp
                ->index.js
    --test
        --java
            --database
                ->JDBCArtistDaoTest.java
            --servlet
                ->IndexServletTest.java
            --testserver
                ->TestServer.java
        --resources

database
    JDBCAlbumDao
        Hallitsee tietokannan Album taulun ja servlettien välistä yhteyttä

    JDBCArtistDao
        Hallitsee tietokannan Artist taulun ja servlettien välistä yhteyttä

    SQLConnection
        Luo yhteyden tietokantaan JDBC luokille.

model
    AlbumItem
        Toimii mallina albumi olioille
    ArtistItem
        Toimii mallina artisti olioille

servlet/JSP sivut
    AlbumServlet + album.jsp
        Toimii albumille/albumeille omana sivustona, näyttää albumille merkatut kappaleet ja antaa mahdollisuuden poistaa albumin kannasta
    ArtistListServlet + artistList.jsp/artistListSearch.jsp
        Toimii "etusivuna" ja näyttää kaikki tietokannan kaikki artistit ja antaa mahdollisuuden lisätä uuden artistin tietokantaan. Yläkulmassa on hakupalkki, joka käyttää artistListSearch.jsp sivua näyttääkseen hakutulokset.
    ArtistServlet + artist.jsp
        Toimii artistille/artisteille omana sivuna ja näyttää kaikki artistin albumit, mahdollistaa artistin poiston ja albumin lisäämisen artistille

app.js
    JS artistien ja albumien poistoa varten




    
