FROM python
COPY . /Mapquest
EXPOSE 5000
RUN python3 -m pip --quiet install flask requests
CMD python3 /Mapquest/web_app.py
