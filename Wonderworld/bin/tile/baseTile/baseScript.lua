function tprint (tbl, indent)
  if not indent then indent = 0 end
  for k, v in pairs(tbl) do
    formatting = string.rep("  ", indent) .. k .. ": "
    if type(v) == "table" then
      print(formatting)
      tprint(v, indent+1)
    elseif type(v) == 'boolean' then
      print(formatting .. tostring(v))		
    elseif type(v) == 'function' then
      print(formatting .. "function")
    else
      print(formatting .. v)
    end
  end
end

function onEntityJoin(entity)
	tprint(entity, 0)
	
	if entity ~= nil and entity["class"] ~= nil then
		if entity["class"] == "player" then
			print("Got Player")
		end
	end	
end

function onEntityLeave(entity)
	tprint(entity, 0)
end

function onTick(delta)
	print("Time since last tick: " .. delta)
end

function onLoading()
	print("Tile loaded " .. self)
end

function onUnloading()
	print("Tile loaded " .. self)
end

