FROM python
RUN useradd -ms /bin/bash pdUser
USER pdUser
WORKDIR /home/pdUser
ENV PATH="/home/devasc/.local/bin:/home/devasc/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin:/home/pdUser/.local/bin"
COPY . /home/pdUser/pd
EXPOSE 5000
RUN python3 -m pip --disable-pip-version-check --quiet install flask --upgrade pip
CMD python3 /home/pdUser/pd/web_app.py
