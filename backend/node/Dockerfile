FROM node:20-alpine
RUN apk add --no-cache chromium nss freetype harfbuzz ttf-freefont
WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production
COPY . .
ENV PUPPETEER_EXECUTABLE_PATH=/usr/bin/chromium-browser
EXPOSE ${PORT:-3005}
CMD ["node", "server.js"]
