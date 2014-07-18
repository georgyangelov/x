interface Enumerable<a>
    def each(& {a ->})

    def map(& {a -> b})
        result = new [b]

        self.each: item
            result.add item
        end

        result
    end
end

class Main
    def main(args [String], env {String: String})
        Global.print "Hello #{args.first}!"
        Global.print("Hai :)")
    end

    def test(hash {String: String}, func {-> String})
        true
    end
end
