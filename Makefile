SHELL=/bin/bash
VERSION=$(shell echo `git describe --abbrev=0 --tags`)
PROJECT_NAME=weixin-public-developer
VERSION_TAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/$(PROJECT_NAME)
VERSIONED_IMAGE=$(IMAGE):$(VERSION_TAG)


CONFIG_DIR=../config

export VERSION
export VERSIONED_IMAGE

clean:
	docker-compose down
	docker-compose rm --force

pull:
	docker pull $(VERSIONED_IMAGE)

push:
	docker push $(VERSIONED_IMAGE)

build: copy
	docker build --no-cache --build-arg VERSION=$(VERSION) \
	-t $(VERSIONED_IMAGE) .

up:
	docker-compose up -d

log:
	docker-compose logs -f

java-update:
	git reset --hard && git pull --all

config: java-update
	cp -rf $(CONFIG_DIR)/* src/main/resources


package: config
	mvn clean package -DskipTests=true

copy: package
	cp -f target/$(PROJECT_NAME)-$(VERSION).jar .


# docker rmi -f $(docker images -f "dangling=true" -q)