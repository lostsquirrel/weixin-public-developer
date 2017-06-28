SHELL=/bin/bash
VERSION=$(shell echo `git describe --abbrev=0 --tags`)
PROJECT_NAME=weixin-public-developer
VERSION_TAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/$(PROJECT_NAME)
VERSIONED_IMAGE=$(IMAGE):$(VERSION_TAG)

STATIC_SOURCE=/home/lisong/sources/web_test_base/cognitive
STATIC_DEPLOY=/var/www/html/demos/

CONFIG_DIR=../config

export VERSION
export VERSIONED_IMAGE

clean:
	docker-compose down
	docker-compose rm --force

pull:
	docker pull $(VERSIONED_IMAGE)

push:
    docker pull $(VERSIONED_IMAGE)
    
build: copy
	docker build --no-cache --build-arg VERSION=$(VERSION) \
	-t $(VERSIONED_IMAGE) .

up: pull
	docker-compose up -d

static-update:
	cd $(STATIC_SOURCE) && git pull

static: static-update
	sudo cp -rf $(STATIC_SOURCE) $(STATIC_DEPLOY)

java-update:
	git reset --hard && git pull --all

config: java-update
	cp -rf $(CONFIG_DIR)/* src/main/resources


package: config
	mvn clean package

copy: package
	cp -f target/$(PROJECT_NAME)-$(VERSION).jar .
