#!/bin/sh
clear
if !(test -d "dockerFiles/") 
then
    mkdir dockerFiles
    if (test -f "web_app.py")
        then cp -R web_app.py dockerFiles/.
        else exit 1
    fi
fi
if !(test -d "dockerFiles/templates")
then
    mkdir dockerFiles/templates;
    if (test -d "templates/")
        then cp -R templates/* dockerFiles/templates/.
        else exit 2
    fi
fi
if !(test -d "dockerFiles/static")
then
    mkdir dockerFiles/static;
    if (test -d "static/")
        then cp -R static/* dockerFiles/static/.
        else exit 3
    fi
fi
cat << EOF > dockerFiles/Dockerfile
FROM python
COPY . /Mapquest
EXPOSE 5000
RUN python3 -m pip --quiet install flask requests
CMD python3 /Mapquest/web_app.py
EOF
cd dockerFiles
if !(test $(docker ps -aq -f name=mapquest))
then docker build -t mapquest .
else
    docker stop mapquest
    docker rm mapquest
    docker build -t mapquest .
fi
if !(test $(netstat -tuln | grep LISTEN | awk '/:5000/' | wc -l) -gt 0)
then docker run -dtp 5000:5000 --name mapquest mapquest
else
    echo "Error: Port 5000 is currently in use"
    exit 4
fi
docker ps -a
exit 0