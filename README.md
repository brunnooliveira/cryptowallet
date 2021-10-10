## Cryptowallet

### Docker build
```console
docker build -t cryptowallet-teste .
```

### Docker runing local
```console
docker run -d --restart=always --name=cryptowallet -p 8090:8080 cryptowallet-teste

docker rm -f cryptowallet
```

