FROM node:16.4.0 AS build
COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN npm install
RUN node ./node_modules/@angular/cli/bin/ng build  

FROM nginx:alpine
EXPOSE 80
COPY --from=build /usr/src/app/dist/. /usr/share/nginx/html/

