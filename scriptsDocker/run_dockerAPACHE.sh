docker run -d -p 8080:80 -v "$PWD":/usr/local/apache2/htdocs/ --name apache httpd:latest
