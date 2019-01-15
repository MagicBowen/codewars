package robot

type ForwardCommand struct{}
func (c ForwardCommand) run(r *Robot) {
	r.x, r.y = r.heading.nextStep(r.x, r.y, ForwardStep)
}

type BackwardCommand struct{}
func (c BackwardCommand) run(r *Robot) {
	r.x, r.y = r.heading.nextStep(r.x, r.y, BackwardStep)
}

type TurnLeftCommand struct{}
func (c TurnLeftCommand) run(r *Robot) {
	r.heading = r.heading.left()
}

type TurnRightCommand struct{}
func (c TurnRightCommand) run(r *Robot) {
	r.heading = r.heading.right()
}

type RepeatCommand struct{
	n uint
	command Command
}
func (c RepeatCommand) run(r *Robot) {
	for c.n > 0 {
		c.command.run(r)
		c.n--
	}
}

type TurnRoundCommand struct {}
func (c TurnRoundCommand) run(r *Robot) {
	r.Execute(RepeatCommand{2, TurnLeftCommand{}})
}

type ForwardsCommand struct {
	n uint
}
func (c ForwardsCommand) run(r *Robot) {
	r.Execute(RepeatCommand{c.n, ForwardCommand{}})
}

type BackwardsCommand struct {
	n uint
}
func (c BackwardsCommand) run(r *Robot) {
	r.Execute(RepeatCommand{c.n, BackwardCommand{}})
}

type Commands struct {
	commands []Command
}
func (c Commands) run(r *Robot) {
	for _, command := range c.commands {
		r.Execute(command)
	}
}