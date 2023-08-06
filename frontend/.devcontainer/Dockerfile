# Use the official Node.js image as a base image
FROM node:14

# Set the working directory inside the container
WORKDIR /app

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Copy package.json and package-lock.json if you have one
# This will speed up the build process by using cached layers for dependencies
COPY package*.json ./

# Install project dependencies
RUN npm install

# Expose port 4200 to access the Angular app
EXPOSE 4200

# Start the Angular development server
CMD ["ng", "serve", "--host", "0.0.0.0"]
