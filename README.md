# Instructions

In order to use this software, you'll need an arduino nano soldered to your a gamecube controller. I highly recommend using a old controller you don't care about, since you won't be able to use it in tournament after this. The other option is an adapter that goes between your gamecube controller and the switch's gamecube adapter. I personally don't use this method, nor do I know exactly how to build one, but it is much less invasive and won't require you to destroy a gamecube controller. There are a lot of people who do know these things though, and you can find them in the custom gamecube controller [discord](http://discord.customg.cc/)!

If you are going to embed the Arduino in your controller like I did, you could follow [this guide](https://docs.google.com/document/d/1KZrORDtJBuovVAHRZjteRitBKkwibZOc7VW0PdGedEk/edit). In order for this particular hex file to work, you need to make sure you use these pinouts: 2 for the console, 3 for the controller.

Once you have an Arduino, you'll want to load the training mode hex file on it. Select "Clone or Download" from the file browser above, and then click "Download as ZIP". The most important file is `arduino_nano_training_mod_v1.hex`. To load it on to your arduino, you can use a tool like [Xloader](http://xloader.russemotto.com/). Make sure you select `Duemilanove/Nano(ATmega328)` as the device, and make sure you're using the correct COM port (it should be the one that shows up when you plug your board's USB cable in).

# Usage

The best way to get started with your new training dummy is in training mode. Though this should theoretically work for any smash game, I'm going to give these instructions assuming you're playing Ultimate. Set the CPU to control, and make sure your training controler is set up to control them. Here's how to cycle through the options

- dpad left: cycle DI through the following options
  - no DI (default)
  - random left/right DI
  - full random DI
  - set DI (hold the control stick while you select this and it will continue to hold that position)
- dpad right: cycle escape options
  - no escape option (default)
  - mash airdodge
  - mash jump

Input recording works a little bit differently. To use it, hold DPad down while you execute a series of inputs (no more than 400 frames, so keep it under about 7 seconds). Then to replay it on a loop, press DPad up. You can press DPad up again to stop playback mode. One thing to keep in mind is that due to the way the game polls inputs, your playback might be _slightly_ different than what you originally recorded, but for simple recordings it works quite fine.

# Input Display (beta)

I've also included a file called gamecube.exe in the repository. It's located inside one of the folders, either the 32 bit or 64 bit version. If you run that, it should open a window on your computer that displays gamecube controller inputs from the arduino'd controller in real time. I haven't tested it on any computer besides my own, and there are definitely some bugs in the code still, so no promises!

![gamecube.exe](https://i.imgur.com/KWxFeNU.png)
# Contact

If anything does or doesn't work for you, please let me know! I've never done a project like this before so I definitely have a lot to learn, and any feedback can only help things get better in the future. To get notified for all of the latest updates to this project, you can follow me on twitter [@captainlpika](https://twitter.com/captainlpika).
